import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRealnameAuthRecord } from 'app/shared/model/realname-auth-record.model';
import { AccountService } from 'app/core';
import { RealnameAuthRecordService } from './realname-auth-record.service';

@Component({
    selector: 'jhi-realname-auth-record',
    templateUrl: './realname-auth-record.component.html'
})
export class RealnameAuthRecordComponent implements OnInit, OnDestroy {
    realnameAuthRecords: IRealnameAuthRecord[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected realnameAuthRecordService: RealnameAuthRecordService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.realnameAuthRecordService.query().subscribe(
            (res: HttpResponse<IRealnameAuthRecord[]>) => {
                this.realnameAuthRecords = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRealnameAuthRecords();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRealnameAuthRecord) {
        return item.id;
    }

    registerChangeInRealnameAuthRecords() {
        this.eventSubscriber = this.eventManager.subscribe('realnameAuthRecordListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
