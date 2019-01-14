import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRealnameInfo } from 'app/shared/model/realname-info.model';
import { RealnameInfoService } from './realname-info.service';

@Component({
    selector: 'jhi-realname-info-update',
    templateUrl: './realname-info-update.component.html'
})
export class RealnameInfoUpdateComponent implements OnInit {
    realnameInfo: IRealnameInfo;
    isSaving: boolean;
    createTime: string;
    lastModifyTime: string;

    constructor(protected realnameInfoService: RealnameInfoService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ realnameInfo }) => {
            this.realnameInfo = realnameInfo;
            this.createTime = this.realnameInfo.createTime != null ? this.realnameInfo.createTime.format(DATE_TIME_FORMAT) : null;
            this.lastModifyTime =
                this.realnameInfo.lastModifyTime != null ? this.realnameInfo.lastModifyTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.realnameInfo.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.realnameInfo.lastModifyTime = this.lastModifyTime != null ? moment(this.lastModifyTime, DATE_TIME_FORMAT) : null;
        if (this.realnameInfo.id !== undefined) {
            this.subscribeToSaveResponse(this.realnameInfoService.update(this.realnameInfo));
        } else {
            this.subscribeToSaveResponse(this.realnameInfoService.create(this.realnameInfo));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRealnameInfo>>) {
        result.subscribe((res: HttpResponse<IRealnameInfo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
