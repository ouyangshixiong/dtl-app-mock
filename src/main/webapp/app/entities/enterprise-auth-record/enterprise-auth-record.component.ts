import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEnterpriseAuthRecord } from 'app/shared/model/enterprise-auth-record.model';
import { AccountService } from 'app/core';
import { EnterpriseAuthRecordService } from './enterprise-auth-record.service';

@Component({
    selector: 'jhi-enterprise-auth-record',
    templateUrl: './enterprise-auth-record.component.html'
})
export class EnterpriseAuthRecordComponent implements OnInit, OnDestroy {
    enterpriseAuthRecords: IEnterpriseAuthRecord[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected enterpriseAuthRecordService: EnterpriseAuthRecordService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.enterpriseAuthRecordService.query().subscribe(
            (res: HttpResponse<IEnterpriseAuthRecord[]>) => {
                this.enterpriseAuthRecords = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEnterpriseAuthRecords();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEnterpriseAuthRecord) {
        return item.id;
    }

    registerChangeInEnterpriseAuthRecords() {
        this.eventSubscriber = this.eventManager.subscribe('enterpriseAuthRecordListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
