import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISiteAuthRecord } from 'app/shared/model/site-auth-record.model';
import { SiteAuthRecordService } from './site-auth-record.service';

@Component({
    selector: 'jhi-site-auth-record-update',
    templateUrl: './site-auth-record-update.component.html'
})
export class SiteAuthRecordUpdateComponent implements OnInit {
    siteAuthRecord: ISiteAuthRecord;
    isSaving: boolean;
    createTime: string;
    lastModifyTime: string;

    constructor(protected siteAuthRecordService: SiteAuthRecordService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ siteAuthRecord }) => {
            this.siteAuthRecord = siteAuthRecord;
            this.createTime = this.siteAuthRecord.createTime != null ? this.siteAuthRecord.createTime.format(DATE_TIME_FORMAT) : null;
            this.lastModifyTime =
                this.siteAuthRecord.lastModifyTime != null ? this.siteAuthRecord.lastModifyTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.siteAuthRecord.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.siteAuthRecord.lastModifyTime = this.lastModifyTime != null ? moment(this.lastModifyTime, DATE_TIME_FORMAT) : null;
        if (this.siteAuthRecord.id !== undefined) {
            this.subscribeToSaveResponse(this.siteAuthRecordService.update(this.siteAuthRecord));
        } else {
            this.subscribeToSaveResponse(this.siteAuthRecordService.create(this.siteAuthRecord));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISiteAuthRecord>>) {
        result.subscribe((res: HttpResponse<ISiteAuthRecord>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
