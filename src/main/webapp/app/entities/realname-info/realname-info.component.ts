import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IRealnameInfo } from 'app/shared/model/realname-info.model';
import { AccountService } from 'app/core';
import { RealnameInfoService } from './realname-info.service';

@Component({
    selector: 'jhi-realname-info',
    templateUrl: './realname-info.component.html'
})
export class RealnameInfoComponent implements OnInit, OnDestroy {
    realnameInfos: IRealnameInfo[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected realnameInfoService: RealnameInfoService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.realnameInfoService.query().subscribe(
            (res: HttpResponse<IRealnameInfo[]>) => {
                this.realnameInfos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInRealnameInfos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRealnameInfo) {
        return item.id;
    }

    registerChangeInRealnameInfos() {
        this.eventSubscriber = this.eventManager.subscribe('realnameInfoListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
