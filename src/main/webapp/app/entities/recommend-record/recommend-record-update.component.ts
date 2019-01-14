import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRecommendRecord } from 'app/shared/model/recommend-record.model';
import { RecommendRecordService } from './recommend-record.service';

@Component({
    selector: 'jhi-recommend-record-update',
    templateUrl: './recommend-record-update.component.html'
})
export class RecommendRecordUpdateComponent implements OnInit {
    recommendRecord: IRecommendRecord;
    isSaving: boolean;
    createTime: string;
    lastModifyTime: string;

    constructor(protected recommendRecordService: RecommendRecordService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ recommendRecord }) => {
            this.recommendRecord = recommendRecord;
            this.createTime = this.recommendRecord.createTime != null ? this.recommendRecord.createTime.format(DATE_TIME_FORMAT) : null;
            this.lastModifyTime =
                this.recommendRecord.lastModifyTime != null ? this.recommendRecord.lastModifyTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.recommendRecord.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.recommendRecord.lastModifyTime = this.lastModifyTime != null ? moment(this.lastModifyTime, DATE_TIME_FORMAT) : null;
        if (this.recommendRecord.id !== undefined) {
            this.subscribeToSaveResponse(this.recommendRecordService.update(this.recommendRecord));
        } else {
            this.subscribeToSaveResponse(this.recommendRecordService.create(this.recommendRecord));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRecommendRecord>>) {
        result.subscribe((res: HttpResponse<IRecommendRecord>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
