import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISiteInfo } from 'app/shared/model/site-info.model';
import { AccountService } from 'app/core';
import { SiteInfoService } from './site-info.service';

@Component({
    selector: 'jhi-site-info',
    templateUrl: './site-info.component.html'
})
export class SiteInfoComponent implements OnInit, OnDestroy {
    siteInfos: ISiteInfo[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected siteInfoService: SiteInfoService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.siteInfoService.query().subscribe(
            (res: HttpResponse<ISiteInfo[]>) => {
                this.siteInfos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSiteInfos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISiteInfo) {
        return item.id;
    }

    registerChangeInSiteInfos() {
        this.eventSubscriber = this.eventManager.subscribe('siteInfoListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
