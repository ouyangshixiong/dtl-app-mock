import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRealnameAuthRecord } from 'app/shared/model/realname-auth-record.model';
import { RealnameAuthRecordService } from './realname-auth-record.service';

@Component({
    selector: 'jhi-realname-auth-record-update',
    templateUrl: './realname-auth-record-update.component.html'
})
export class RealnameAuthRecordUpdateComponent implements OnInit {
    realnameAuthRecord: IRealnameAuthRecord;
    isSaving: boolean;
    createTime: string;
    lastModifyTime: string;

    constructor(protected realnameAuthRecordService: RealnameAuthRecordService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ realnameAuthRecord }) => {
            this.realnameAuthRecord = realnameAuthRecord;
            this.createTime =
                this.realnameAuthRecord.createTime != null ? this.realnameAuthRecord.createTime.format(DATE_TIME_FORMAT) : null;
            this.lastModifyTime =
                this.realnameAuthRecord.lastModifyTime != null ? this.realnameAuthRecord.lastModifyTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.realnameAuthRecord.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.realnameAuthRecord.lastModifyTime = this.lastModifyTime != null ? moment(this.lastModifyTime, DATE_TIME_FORMAT) : null;
        if (this.realnameAuthRecord.id !== undefined) {
            this.subscribeToSaveResponse(this.realnameAuthRecordService.update(this.realnameAuthRecord));
        } else {
            this.subscribeToSaveResponse(this.realnameAuthRecordService.create(this.realnameAuthRecord));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRealnameAuthRecord>>) {
        result.subscribe((res: HttpResponse<IRealnameAuthRecord>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
