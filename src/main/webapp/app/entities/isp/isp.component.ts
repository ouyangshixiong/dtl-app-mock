import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IIsp } from 'app/shared/model/isp.model';
import { AccountService } from 'app/core';
import { IspService } from './isp.service';

@Component({
    selector: 'jhi-isp',
    templateUrl: './isp.component.html'
})
export class IspComponent implements OnInit, OnDestroy {
    isps: IIsp[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected ispService: IspService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.ispService.query().subscribe(
            (res: HttpResponse<IIsp[]>) => {
                this.isps = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInIsps();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IIsp) {
        return item.id;
    }

    registerChangeInIsps() {
        this.eventSubscriber = this.eventManager.subscribe('ispListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
