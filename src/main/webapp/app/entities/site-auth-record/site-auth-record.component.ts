import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISiteAuthRecord } from 'app/shared/model/site-auth-record.model';
import { AccountService } from 'app/core';
import { SiteAuthRecordService } from './site-auth-record.service';

@Component({
    selector: 'jhi-site-auth-record',
    templateUrl: './site-auth-record.component.html'
})
export class SiteAuthRecordComponent implements OnInit, OnDestroy {
    siteAuthRecords: ISiteAuthRecord[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected siteAuthRecordService: SiteAuthRecordService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.siteAuthRecordService.query().subscribe(
            (res: HttpResponse<ISiteAuthRecord[]>) => {
                this.siteAuthRecords = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSiteAuthRecords();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISiteAuthRecord) {
        return item.id;
    }

    registerChangeInSiteAuthRecords() {
        this.eventSubscriber = this.eventManager.subscribe('siteAuthRecordListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
