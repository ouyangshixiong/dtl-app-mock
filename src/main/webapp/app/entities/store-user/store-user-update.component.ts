import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IStoreUser } from 'app/shared/model/store-user.model';
import { StoreUserService } from './store-user.service';

@Component({
    selector: 'jhi-store-user-update',
    templateUrl: './store-user-update.component.html'
})
export class StoreUserUpdateComponent implements OnInit {
    storeUser: IStoreUser;
    isSaving: boolean;

    constructor(protected storeUserService: StoreUserService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ storeUser }) => {
            this.storeUser = storeUser;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.storeUser.id !== undefined) {
            this.subscribeToSaveResponse(this.storeUserService.update(this.storeUser));
        } else {
            this.subscribeToSaveResponse(this.storeUserService.create(this.storeUser));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IStoreUser>>) {
        result.subscribe((res: HttpResponse<IStoreUser>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
