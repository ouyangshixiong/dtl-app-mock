import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEnterpriseInfo } from 'app/shared/model/enterprise-info.model';
import { AccountService } from 'app/core';
import { EnterpriseInfoService } from './enterprise-info.service';

@Component({
    selector: 'jhi-enterprise-info',
    templateUrl: './enterprise-info.component.html'
})
export class EnterpriseInfoComponent implements OnInit, OnDestroy {
    enterpriseInfos: IEnterpriseInfo[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected enterpriseInfoService: EnterpriseInfoService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.enterpriseInfoService.query().subscribe(
            (res: HttpResponse<IEnterpriseInfo[]>) => {
                this.enterpriseInfos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEnterpriseInfos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEnterpriseInfo) {
        return item.id;
    }

    registerChangeInEnterpriseInfos() {
        this.eventSubscriber = this.eventManager.subscribe('enterpriseInfoListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
