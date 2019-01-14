import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IUserRole } from 'app/shared/model/user-role.model';
import { AccountService } from 'app/core';
import { UserRoleService } from './user-role.service';

@Component({
    selector: 'jhi-user-role',
    templateUrl: './user-role.component.html'
})
export class UserRoleComponent implements OnInit, OnDestroy {
    userRoles: IUserRole[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected userRoleService: UserRoleService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.userRoleService.query().subscribe(
            (res: HttpResponse<IUserRole[]>) => {
                this.userRoles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInUserRoles();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IUserRole) {
        return item.id;
    }

    registerChangeInUserRoles() {
        this.eventSubscriber = this.eventManager.subscribe('userRoleListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
