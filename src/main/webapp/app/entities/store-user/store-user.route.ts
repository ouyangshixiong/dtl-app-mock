import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StoreUser } from 'app/shared/model/store-user.model';
import { StoreUserService } from './store-user.service';
import { StoreUserComponent } from './store-user.component';
import { StoreUserDetailComponent } from './store-user-detail.component';
import { StoreUserUpdateComponent } from './store-user-update.component';
import { StoreUserDeletePopupComponent } from './store-user-delete-dialog.component';
import { IStoreUser } from 'app/shared/model/store-user.model';

@Injectable({ providedIn: 'root' })
export class StoreUserResolve implements Resolve<IStoreUser> {
    constructor(private service: StoreUserService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<StoreUser> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<StoreUser>) => response.ok),
                map((storeUser: HttpResponse<StoreUser>) => storeUser.body)
            );
        }
        return of(new StoreUser());
    }
}

export const storeUserRoute: Routes = [
    {
        path: 'store-user',
        component: StoreUserComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.storeUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'store-user/:id/view',
        component: StoreUserDetailComponent,
        resolve: {
            storeUser: StoreUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.storeUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'store-user/new',
        component: StoreUserUpdateComponent,
        resolve: {
            storeUser: StoreUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.storeUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'store-user/:id/edit',
        component: StoreUserUpdateComponent,
        resolve: {
            storeUser: StoreUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.storeUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const storeUserPopupRoute: Routes = [
    {
        path: 'store-user/:id/delete',
        component: StoreUserDeletePopupComponent,
        resolve: {
            storeUser: StoreUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.storeUser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
