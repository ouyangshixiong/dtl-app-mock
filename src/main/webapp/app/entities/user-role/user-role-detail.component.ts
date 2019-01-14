import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserRole } from 'app/shared/model/user-role.model';

@Component({
    selector: 'jhi-user-role-detail',
    templateUrl: './user-role-detail.component.html'
})
export class UserRoleDetailComponent implements OnInit {
    userRole: IUserRole;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ userRole }) => {
            this.userRole = userRole;
        });
    }

    previousState() {
        window.history.back();
    }
}
