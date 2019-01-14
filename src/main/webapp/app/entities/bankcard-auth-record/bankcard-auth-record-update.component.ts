import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBankcardAuthRecord } from 'app/shared/model/bankcard-auth-record.model';
import { BankcardAuthRecordService } from './bankcard-auth-record.service';

@Component({
    selector: 'jhi-bankcard-auth-record-update',
    templateUrl: './bankcard-auth-record-update.component.html'
})
export class BankcardAuthRecordUpdateComponent implements OnInit {
    bankcardAuthRecord: IBankcardAuthRecord;
    isSaving: boolean;
    createTime: string;
    lastModifyTime: string;

    constructor(protected bankcardAuthRecordService: BankcardAuthRecordService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ bankcardAuthRecord }) => {
            this.bankcardAuthRecord = bankcardAuthRecord;
            this.createTime =
                this.bankcardAuthRecord.createTime != null ? this.bankcardAuthRecord.createTime.format(DATE_TIME_FORMAT) : null;
            this.lastModifyTime =
                this.bankcardAuthRecord.lastModifyTime != null ? this.bankcardAuthRecord.lastModifyTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.bankcardAuthRecord.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.bankcardAuthRecord.lastModifyTime = this.lastModifyTime != null ? moment(this.lastModifyTime, DATE_TIME_FORMAT) : null;
        if (this.bankcardAuthRecord.id !== undefined) {
            this.subscribeToSaveResponse(this.bankcardAuthRecordService.update(this.bankcardAuthRecord));
        } else {
            this.subscribeToSaveResponse(this.bankcardAuthRecordService.create(this.bankcardAuthRecord));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IBankcardAuthRecord>>) {
        result.subscribe((res: HttpResponse<IBankcardAuthRecord>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
