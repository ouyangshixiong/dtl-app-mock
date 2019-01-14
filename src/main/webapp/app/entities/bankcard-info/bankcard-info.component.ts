import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IBankcardInfo } from 'app/shared/model/bankcard-info.model';
import { AccountService } from 'app/core';
import { BankcardInfoService } from './bankcard-info.service';

@Component({
    selector: 'jhi-bankcard-info',
    templateUrl: './bankcard-info.component.html'
})
export class BankcardInfoComponent implements OnInit, OnDestroy {
    bankcardInfos: IBankcardInfo[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected bankcardInfoService: BankcardInfoService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.bankcardInfoService.query().subscribe(
            (res: HttpResponse<IBankcardInfo[]>) => {
                this.bankcardInfos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInBankcardInfos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IBankcardInfo) {
        return item.id;
    }

    registerChangeInBankcardInfos() {
        this.eventSubscriber = this.eventManager.subscribe('bankcardInfoListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
