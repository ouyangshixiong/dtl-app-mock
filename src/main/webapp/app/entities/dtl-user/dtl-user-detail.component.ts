import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDTLUser } from 'app/shared/model/dtl-user.model';

@Component({
    selector: 'jhi-dtl-user-detail',
    templateUrl: './dtl-user-detail.component.html'
})
export class DTLUserDetailComponent implements OnInit {
    dTLUser: IDTLUser;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dTLUser }) => {
            this.dTLUser = dTLUser;
        });
    }

    previousState() {
        window.history.back();
    }
}
