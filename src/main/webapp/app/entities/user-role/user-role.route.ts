import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { UserRole } from 'app/shared/model/user-role.model';
import { UserRoleService } from './user-role.service';
import { UserRoleComponent } from './user-role.component';
import { UserRoleDetailComponent } from './user-role-detail.component';
import { UserRoleUpdateComponent } from './user-role-update.component';
import { UserRoleDeletePopupComponent } from './user-role-delete-dialog.component';
import { IUserRole } from 'app/shared/model/user-role.model';

@Injectable({ providedIn: 'root' })
export class UserRoleResolve implements Resolve<IUserRole> {
    constructor(private service: UserRoleService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<UserRole> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<UserRole>) => response.ok),
                map((userRole: HttpResponse<UserRole>) => userRole.body)
            );
        }
        return of(new UserRole());
    }
}

export const userRoleRoute: Routes = [
    {
        path: 'user-role',
        component: UserRoleComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.userRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-role/:id/view',
        component: UserRoleDetailComponent,
        resolve: {
            userRole: UserRoleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.userRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-role/new',
        component: UserRoleUpdateComponent,
        resolve: {
            userRole: UserRoleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.userRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-role/:id/edit',
        component: UserRoleUpdateComponent,
        resolve: {
            userRole: UserRoleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.userRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const userRolePopupRoute: Routes = [
    {
        path: 'user-role/:id/delete',
        component: UserRoleDeletePopupComponent,
        resolve: {
            userRole: UserRoleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.userRole.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
