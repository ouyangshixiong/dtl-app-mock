import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBankcardInfo } from 'app/shared/model/bankcard-info.model';
import { BankcardInfoService } from './bankcard-info.service';

@Component({
    selector: 'jhi-bankcard-info-update',
    templateUrl: './bankcard-info-update.component.html'
})
export class BankcardInfoUpdateComponent implements OnInit {
    bankcardInfo: IBankcardInfo;
    isSaving: boolean;
    createTime: string;
    lastModifyTime: string;

    constructor(protected bankcardInfoService: BankcardInfoService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ bankcardInfo }) => {
            this.bankcardInfo = bankcardInfo;
            this.createTime = this.bankcardInfo.createTime != null ? this.bankcardInfo.createTime.format(DATE_TIME_FORMAT) : null;
            this.lastModifyTime =
                this.bankcardInfo.lastModifyTime != null ? this.bankcardInfo.lastModifyTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.bankcardInfo.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.bankcardInfo.lastModifyTime = this.lastModifyTime != null ? moment(this.lastModifyTime, DATE_TIME_FORMAT) : null;
        if (this.bankcardInfo.id !== undefined) {
            this.subscribeToSaveResponse(this.bankcardInfoService.update(this.bankcardInfo));
        } else {
            this.subscribeToSaveResponse(this.bankcardInfoService.create(this.bankcardInfo));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IBankcardInfo>>) {
        result.subscribe((res: HttpResponse<IBankcardInfo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
