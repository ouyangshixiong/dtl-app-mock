import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IDTLUser } from 'app/shared/model/dtl-user.model';
import { DTLUserService } from './dtl-user.service';

@Component({
    selector: 'jhi-dtl-user-update',
    templateUrl: './dtl-user-update.component.html'
})
export class DTLUserUpdateComponent implements OnInit {
    dTLUser: IDTLUser;
    isSaving: boolean;
    regTime: string;
    lastLoginTime: string;

    constructor(protected dTLUserService: DTLUserService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ dTLUser }) => {
            this.dTLUser = dTLUser;
            this.regTime = this.dTLUser.regTime != null ? this.dTLUser.regTime.format(DATE_TIME_FORMAT) : null;
            this.lastLoginTime = this.dTLUser.lastLoginTime != null ? this.dTLUser.lastLoginTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.dTLUser.regTime = this.regTime != null ? moment(this.regTime, DATE_TIME_FORMAT) : null;
        this.dTLUser.lastLoginTime = this.lastLoginTime != null ? moment(this.lastLoginTime, DATE_TIME_FORMAT) : null;
        if (this.dTLUser.id !== undefined) {
            this.subscribeToSaveResponse(this.dTLUserService.update(this.dTLUser));
        } else {
            this.subscribeToSaveResponse(this.dTLUserService.create(this.dTLUser));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDTLUser>>) {
        result.subscribe((res: HttpResponse<IDTLUser>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
