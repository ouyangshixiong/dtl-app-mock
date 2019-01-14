import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IStaff } from 'app/shared/model/staff.model';
import { StaffService } from './staff.service';

@Component({
    selector: 'jhi-staff-update',
    templateUrl: './staff-update.component.html'
})
export class StaffUpdateComponent implements OnInit {
    staff: IStaff;
    isSaving: boolean;
    createTime: string;
    lastModifyTime: string;

    constructor(protected staffService: StaffService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ staff }) => {
            this.staff = staff;
            this.createTime = this.staff.createTime != null ? this.staff.createTime.format(DATE_TIME_FORMAT) : null;
            this.lastModifyTime = this.staff.lastModifyTime != null ? this.staff.lastModifyTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.staff.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.staff.lastModifyTime = this.lastModifyTime != null ? moment(this.lastModifyTime, DATE_TIME_FORMAT) : null;
        if (this.staff.id !== undefined) {
            this.subscribeToSaveResponse(this.staffService.update(this.staff));
        } else {
            this.subscribeToSaveResponse(this.staffService.create(this.staff));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IStaff>>) {
        result.subscribe((res: HttpResponse<IStaff>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
