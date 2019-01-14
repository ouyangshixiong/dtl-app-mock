import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IProductRecord } from 'app/shared/model/product-record.model';
import { ProductRecordService } from './product-record.service';

@Component({
    selector: 'jhi-product-record-update',
    templateUrl: './product-record-update.component.html'
})
export class ProductRecordUpdateComponent implements OnInit {
    productRecord: IProductRecord;
    isSaving: boolean;
    createTime: string;
    lastModifyTime: string;

    constructor(protected productRecordService: ProductRecordService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ productRecord }) => {
            this.productRecord = productRecord;
            this.createTime = this.productRecord.createTime != null ? this.productRecord.createTime.format(DATE_TIME_FORMAT) : null;
            this.lastModifyTime =
                this.productRecord.lastModifyTime != null ? this.productRecord.lastModifyTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.productRecord.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.productRecord.lastModifyTime = this.lastModifyTime != null ? moment(this.lastModifyTime, DATE_TIME_FORMAT) : null;
        if (this.productRecord.id !== undefined) {
            this.subscribeToSaveResponse(this.productRecordService.update(this.productRecord));
        } else {
            this.subscribeToSaveResponse(this.productRecordService.create(this.productRecord));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductRecord>>) {
        result.subscribe((res: HttpResponse<IProductRecord>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
