import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { UserLevel } from 'app/shared/model/user-level.model';
import { UserLevelService } from './user-level.service';
import { UserLevelComponent } from './user-level.component';
import { UserLevelDetailComponent } from './user-level-detail.component';
import { UserLevelUpdateComponent } from './user-level-update.component';
import { UserLevelDeletePopupComponent } from './user-level-delete-dialog.component';
import { IUserLevel } from 'app/shared/model/user-level.model';

@Injectable({ providedIn: 'root' })
export class UserLevelResolve implements Resolve<IUserLevel> {
    constructor(private service: UserLevelService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<UserLevel> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<UserLevel>) => response.ok),
                map((userLevel: HttpResponse<UserLevel>) => userLevel.body)
            );
        }
        return of(new UserLevel());
    }
}

export const userLevelRoute: Routes = [
    {
        path: 'user-level',
        component: UserLevelComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.userLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-level/:id/view',
        component: UserLevelDetailComponent,
        resolve: {
            userLevel: UserLevelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.userLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-level/new',
        component: UserLevelUpdateComponent,
        resolve: {
            userLevel: UserLevelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.userLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-level/:id/edit',
        component: UserLevelUpdateComponent,
        resolve: {
            userLevel: UserLevelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.userLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const userLevelPopupRoute: Routes = [
    {
        path: 'user-level/:id/delete',
        component: UserLevelDeletePopupComponent,
        resolve: {
            userLevel: UserLevelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.userLevel.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
