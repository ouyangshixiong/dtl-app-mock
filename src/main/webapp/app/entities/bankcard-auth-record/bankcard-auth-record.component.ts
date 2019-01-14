import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IBankcardAuthRecord } from 'app/shared/model/bankcard-auth-record.model';
import { AccountService } from 'app/core';
import { BankcardAuthRecordService } from './bankcard-auth-record.service';

@Component({
    selector: 'jhi-bankcard-auth-record',
    templateUrl: './bankcard-auth-record.component.html'
})
export class BankcardAuthRecordComponent implements OnInit, OnDestroy {
    bankcardAuthRecords: IBankcardAuthRecord[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected bankcardAuthRecordService: BankcardAuthRecordService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.bankcardAuthRecordService.query().subscribe(
            (res: HttpResponse<IBankcardAuthRecord[]>) => {
                this.bankcardAuthRecords = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInBankcardAuthRecords();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IBankcardAuthRecord) {
        return item.id;
    }

    registerChangeInBankcardAuthRecords() {
        this.eventSubscriber = this.eventManager.subscribe('bankcardAuthRecordListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
