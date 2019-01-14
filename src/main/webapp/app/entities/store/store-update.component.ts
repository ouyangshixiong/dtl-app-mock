import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IStore } from 'app/shared/model/store.model';
import { StoreService } from './store.service';

@Component({
    selector: 'jhi-store-update',
    templateUrl: './store-update.component.html'
})
export class StoreUpdateComponent implements OnInit {
    store: IStore;
    isSaving: boolean;
    createTime: string;
    lastModifyTime: string;

    constructor(protected storeService: StoreService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ store }) => {
            this.store = store;
            this.createTime = this.store.createTime != null ? this.store.createTime.format(DATE_TIME_FORMAT) : null;
            this.lastModifyTime = this.store.lastModifyTime != null ? this.store.lastModifyTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.store.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.store.lastModifyTime = this.lastModifyTime != null ? moment(this.lastModifyTime, DATE_TIME_FORMAT) : null;
        if (this.store.id !== undefined) {
            this.subscribeToSaveResponse(this.storeService.update(this.store));
        } else {
            this.subscribeToSaveResponse(this.storeService.create(this.store));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IStore>>) {
        result.subscribe((res: HttpResponse<IStore>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
