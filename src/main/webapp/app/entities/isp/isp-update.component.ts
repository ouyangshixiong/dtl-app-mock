import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IIsp } from 'app/shared/model/isp.model';
import { IspService } from './isp.service';

@Component({
    selector: 'jhi-isp-update',
    templateUrl: './isp-update.component.html'
})
export class IspUpdateComponent implements OnInit {
    isp: IIsp;
    isSaving: boolean;
    createTime: string;

    constructor(protected ispService: IspService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ isp }) => {
            this.isp = isp;
            this.createTime = this.isp.createTime != null ? this.isp.createTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.isp.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        if (this.isp.id !== undefined) {
            this.subscribeToSaveResponse(this.ispService.update(this.isp));
        } else {
            this.subscribeToSaveResponse(this.ispService.create(this.isp));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IIsp>>) {
        result.subscribe((res: HttpResponse<IIsp>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
