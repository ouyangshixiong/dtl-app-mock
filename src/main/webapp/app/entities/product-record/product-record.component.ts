import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IProductRecord } from 'app/shared/model/product-record.model';
import { AccountService } from 'app/core';
import { ProductRecordService } from './product-record.service';

@Component({
    selector: 'jhi-product-record',
    templateUrl: './product-record.component.html'
})
export class ProductRecordComponent implements OnInit, OnDestroy {
    productRecords: IProductRecord[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected productRecordService: ProductRecordService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.productRecordService.query().subscribe(
            (res: HttpResponse<IProductRecord[]>) => {
                this.productRecords = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInProductRecords();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IProductRecord) {
        return item.id;
    }

    registerChangeInProductRecords() {
        this.eventSubscriber = this.eventManager.subscribe('productRecordListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
