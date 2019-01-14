import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IStoreAuthRecord } from 'app/shared/model/store-auth-record.model';
import { StoreAuthRecordService } from './store-auth-record.service';

@Component({
    selector: 'jhi-store-auth-record-update',
    templateUrl: './store-auth-record-update.component.html'
})
export class StoreAuthRecordUpdateComponent implements OnInit {
    storeAuthRecord: IStoreAuthRecord;
    isSaving: boolean;
    createTime: string;
    lastModifyTime: string;

    constructor(protected storeAuthRecordService: StoreAuthRecordService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ storeAuthRecord }) => {
            this.storeAuthRecord = storeAuthRecord;
            this.createTime = this.storeAuthRecord.createTime != null ? this.storeAuthRecord.createTime.format(DATE_TIME_FORMAT) : null;
            this.lastModifyTime =
                this.storeAuthRecord.lastModifyTime != null ? this.storeAuthRecord.lastModifyTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.storeAuthRecord.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.storeAuthRecord.lastModifyTime = this.lastModifyTime != null ? moment(this.lastModifyTime, DATE_TIME_FORMAT) : null;
        if (this.storeAuthRecord.id !== undefined) {
            this.subscribeToSaveResponse(this.storeAuthRecordService.update(this.storeAuthRecord));
        } else {
            this.subscribeToSaveResponse(this.storeAuthRecordService.create(this.storeAuthRecord));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IStoreAuthRecord>>) {
        result.subscribe((res: HttpResponse<IStoreAuthRecord>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
