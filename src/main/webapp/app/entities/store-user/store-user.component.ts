import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IStoreUser } from 'app/shared/model/store-user.model';
import { AccountService } from 'app/core';
import { StoreUserService } from './store-user.service';

@Component({
    selector: 'jhi-store-user',
    templateUrl: './store-user.component.html'
})
export class StoreUserComponent implements OnInit, OnDestroy {
    storeUsers: IStoreUser[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected storeUserService: StoreUserService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.storeUserService.query().subscribe(
            (res: HttpResponse<IStoreUser[]>) => {
                this.storeUsers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInStoreUsers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IStoreUser) {
        return item.id;
    }

    registerChangeInStoreUsers() {
        this.eventSubscriber = this.eventManager.subscribe('storeUserListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
