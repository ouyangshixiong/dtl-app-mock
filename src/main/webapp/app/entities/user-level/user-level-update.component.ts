import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IUserLevel } from 'app/shared/model/user-level.model';
import { UserLevelService } from './user-level.service';

@Component({
    selector: 'jhi-user-level-update',
    templateUrl: './user-level-update.component.html'
})
export class UserLevelUpdateComponent implements OnInit {
    userLevel: IUserLevel;
    isSaving: boolean;
    createTime: string;
    lastModifyTime: string;

    constructor(protected userLevelService: UserLevelService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ userLevel }) => {
            this.userLevel = userLevel;
            this.createTime = this.userLevel.createTime != null ? this.userLevel.createTime.format(DATE_TIME_FORMAT) : null;
            this.lastModifyTime = this.userLevel.lastModifyTime != null ? this.userLevel.lastModifyTime.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.userLevel.createTime = this.createTime != null ? moment(this.createTime, DATE_TIME_FORMAT) : null;
        this.userLevel.lastModifyTime = this.lastModifyTime != null ? moment(this.lastModifyTime, DATE_TIME_FORMAT) : null;
        if (this.userLevel.id !== undefined) {
            this.subscribeToSaveResponse(this.userLevelService.update(this.userLevel));
        } else {
            this.subscribeToSaveResponse(this.userLevelService.create(this.userLevel));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserLevel>>) {
        result.subscribe((res: HttpResponse<IUserLevel>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
