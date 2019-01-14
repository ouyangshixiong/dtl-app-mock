import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IStoreAuthRecord } from 'app/shared/model/store-auth-record.model';
import { AccountService } from 'app/core';
import { StoreAuthRecordService } from './store-auth-record.service';

@Component({
    selector: 'jhi-store-auth-record',
    templateUrl: './store-auth-record.component.html'
})
export class StoreAuthRecordComponent implements OnInit, OnDestroy {
    storeAuthRecords: IStoreAuthRecord[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected storeAuthRecordService: StoreAuthRecordService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.storeAuthRecordService.query().subscribe(
            (res: HttpResponse<IStoreAuthRecord[]>) => {
                this.storeAuthRecords = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInStoreAuthRecords();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IStoreAuthRecord) {
        return item.id;
    }

    registerChangeInStoreAuthRecords() {
        this.eventSubscriber = this.eventManager.subscribe('storeAuthRecordListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
