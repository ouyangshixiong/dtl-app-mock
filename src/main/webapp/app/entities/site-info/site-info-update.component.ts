import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISiteInfo } from 'app/shared/model/site-info.model';
import { SiteInfoService } from './site-info.service';

@Component({
    selector: 'jhi-site-info-update',
    templateUrl: './site-info-update.component.html'
})
export class SiteInfoUpdateComponent implements OnInit {
    siteInfo: ISiteInfo;
    isSaving: boolean;
    createTime: string;
    lastModifyTime: string;

    constructor(protected siteInfoService: SiteInfoService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ siteInfo }) => {
            this.siteInfo = siteInfo;
            this.createTime = this.siteInfo.createTime != null ? this.siteInfo.createTime.format(DATE_TIME_FORMAT) : null;
            this.lastModifyTime = this.siteInfo.lastModifyTime != null ? this.siteInfo.lastModifyTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.siteInfo.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.siteInfo.lastModifyTime = this.lastModifyTime != null ? moment(this.lastModifyTime, DATE_TIME_FORMAT) : null;
        if (this.siteInfo.id !== undefined) {
            this.subscribeToSaveResponse(this.siteInfoService.update(this.siteInfo));
        } else {
            this.subscribeToSaveResponse(this.siteInfoService.create(this.siteInfo));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISiteInfo>>) {
        result.subscribe((res: HttpResponse<ISiteInfo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
