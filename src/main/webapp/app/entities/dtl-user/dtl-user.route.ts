import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DTLUser } from 'app/shared/model/dtl-user.model';
import { DTLUserService } from './dtl-user.service';
import { DTLUserComponent } from './dtl-user.component';
import { DTLUserDetailComponent } from './dtl-user-detail.component';
import { DTLUserUpdateComponent } from './dtl-user-update.component';
import { DTLUserDeletePopupComponent } from './dtl-user-delete-dialog.component';
import { IDTLUser } from 'app/shared/model/dtl-user.model';

@Injectable({ providedIn: 'root' })
export class DTLUserResolve implements Resolve<IDTLUser> {
    constructor(private service: DTLUserService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<DTLUser> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DTLUser>) => response.ok),
                map((dTLUser: HttpResponse<DTLUser>) => dTLUser.body)
            );
        }
        return of(new DTLUser());
    }
}

export const dTLUserRoute: Routes = [
    {
        path: 'dtl-user',
        component: DTLUserComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.dTLUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dtl-user/:id/view',
        component: DTLUserDetailComponent,
        resolve: {
            dTLUser: DTLUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.dTLUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dtl-user/new',
        component: DTLUserUpdateComponent,
        resolve: {
            dTLUser: DTLUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.dTLUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dtl-user/:id/edit',
        component: DTLUserUpdateComponent,
        resolve: {
            dTLUser: DTLUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.dTLUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const dTLUserPopupRoute: Routes = [
    {
        path: 'dtl-user/:id/delete',
        component: DTLUserDeletePopupComponent,
        resolve: {
            dTLUser: DTLUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.dTLUser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
