import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEnterpriseAuthRecord } from 'app/shared/model/enterprise-auth-record.model';
import { EnterpriseAuthRecordService } from './enterprise-auth-record.service';

@Component({
    selector: 'jhi-enterprise-auth-record-update',
    templateUrl: './enterprise-auth-record-update.component.html'
})
export class EnterpriseAuthRecordUpdateComponent implements OnInit {
    enterpriseAuthRecord: IEnterpriseAuthRecord;
    isSaving: boolean;
    createTime: string;
    lastModifyTime: string;

    constructor(protected enterpriseAuthRecordService: EnterpriseAuthRecordService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ enterpriseAuthRecord }) => {
            this.enterpriseAuthRecord = enterpriseAuthRecord;
            this.createTime =
                this.enterpriseAuthRecord.createTime != null ? this.enterpriseAuthRecord.createTime.format(DATE_TIME_FORMAT) : null;
            this.lastModifyTime =
                this.enterpriseAuthRecord.lastModifyTime != null ? this.enterpriseAuthRecord.lastModifyTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.enterpriseAuthRecord.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.enterpriseAuthRecord.lastModifyTime = this.lastModifyTime != null ? moment(this.lastModifyTime, DATE_TIME_FORMAT) : null;
        if (this.enterpriseAuthRecord.id !== undefined) {
            this.subscribeToSaveResponse(this.enterpriseAuthRecordService.update(this.enterpriseAuthRecord));
        } else {
            this.subscribeToSaveResponse(this.enterpriseAuthRecordService.create(this.enterpriseAuthRecord));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEnterpriseAuthRecord>>) {
        result.subscribe(
            (res: HttpResponse<IEnterpriseAuthRecord>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
