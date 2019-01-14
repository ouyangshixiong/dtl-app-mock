import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IUserRole } from 'app/shared/model/user-role.model';
import { UserRoleService } from './user-role.service';

@Component({
    selector: 'jhi-user-role-update',
    templateUrl: './user-role-update.component.html'
})
export class UserRoleUpdateComponent implements OnInit {
    userRole: IUserRole;
    isSaving: boolean;
    createTime: string;
    lastModifyTime: string;

    constructor(protected userRoleService: UserRoleService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ userRole }) => {
            this.userRole = userRole;
            this.createTime = this.userRole.createTime != null ? this.userRole.createTime.format(DATE_TIME_FORMAT) : null;
            this.lastModifyTime = this.userRole.lastModifyTime != null ? this.userRole.lastModifyTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.userRole.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.userRole.lastModifyTime = this.lastModifyTime != null ? moment(this.lastModifyTime, DATE_TIME_FORMAT) : null;
        if (this.userRole.id !== undefined) {
            this.subscribeToSaveResponse(this.userRoleService.update(this.userRole));
        } else {
            this.subscribeToSaveResponse(this.userRoleService.create(this.userRole));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserRole>>) {
        result.subscribe((res: HttpResponse<IUserRole>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
