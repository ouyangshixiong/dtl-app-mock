import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStoreUser } from 'app/shared/model/store-user.model';

@Component({
    selector: 'jhi-store-user-detail',
    templateUrl: './store-user-detail.component.html'
})
export class StoreUserDetailComponent implements OnInit {
    storeUser: IStoreUser;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ storeUser }) => {
            this.storeUser = storeUser;
        });
    }

    previousState() {
        window.history.back();
    }
}
