import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRecommendRecord } from 'app/shared/model/recommend-record.model';
import { AccountService } from 'app/core';
import { RecommendRecordService } from './recommend-record.service';

@Component({
    selector: 'jhi-recommend-record',
    templateUrl: './recommend-record.component.html'
})
export class RecommendRecordComponent implements OnInit, OnDestroy {
    recommendRecords: IRecommendRecord[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected recommendRecordService: RecommendRecordService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.recommendRecordService.query().subscribe(
            (res: HttpResponse<IRecommendRecord[]>) => {
                this.recommendRecords = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRecommendRecords();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRecommendRecord) {
        return item.id;
    }

    registerChangeInRecommendRecords() {
        this.eventSubscriber = this.eventManager.subscribe('recommendRecordListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
