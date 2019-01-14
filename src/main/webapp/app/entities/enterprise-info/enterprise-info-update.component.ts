import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEnterpriseInfo } from 'app/shared/model/enterprise-info.model';
import { EnterpriseInfoService } from './enterprise-info.service';

@Component({
    selector: 'jhi-enterprise-info-update',
    templateUrl: './enterprise-info-update.component.html'
})
export class EnterpriseInfoUpdateComponent implements OnInit {
    enterpriseInfo: IEnterpriseInfo;
    isSaving: boolean;
    createTime: string;
    lastModifyTime: string;

    constructor(protected enterpriseInfoService: EnterpriseInfoService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ enterpriseInfo }) => {
            this.enterpriseInfo = enterpriseInfo;
            this.createTime = this.enterpriseInfo.createTime != null ? this.enterpriseInfo.createTime.format(DATE_TIME_FORMAT) : null;
            this.lastModifyTime =
                this.enterpriseInfo.lastModifyTime != null ? this.enterpriseInfo.lastModifyTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.enterpriseInfo.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.enterpriseInfo.lastModifyTime = this.lastModifyTime != null ? moment(this.lastModifyTime, DATE_TIME_FORMAT) : null;
        if (this.enterpriseInfo.id !== undefined) {
            this.subscribeToSaveResponse(this.enterpriseInfoService.update(this.enterpriseInfo));
        } else {
            this.subscribeToSaveResponse(this.enterpriseInfoService.create(this.enterpriseInfo));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEnterpriseInfo>>) {
        result.subscribe((res: HttpResponse<IEnterpriseInfo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
