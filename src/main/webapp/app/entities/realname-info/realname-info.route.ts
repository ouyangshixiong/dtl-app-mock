import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RealnameInfo } from 'app/shared/model/realname-info.model';
import { RealnameInfoService } from './realname-info.service';
import { RealnameInfoComponent } from './realname-info.component';
import { RealnameInfoDetailComponent } from './realname-info-detail.component';
import { RealnameInfoUpdateComponent } from './realname-info-update.component';
import { RealnameInfoDeletePopupComponent } from './realname-info-delete-dialog.component';
import { IRealnameInfo } from 'app/shared/model/realname-info.model';

@Injectable({ providedIn: 'root' })
export class RealnameInfoResolve implements Resolve<IRealnameInfo> {
    constructor(private service: RealnameInfoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RealnameInfo> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RealnameInfo>) => response.ok),
                map((realnameInfo: HttpResponse<RealnameInfo>) => realnameInfo.body)
            );
        }
        return of(new RealnameInfo());
    }
}

export const realnameInfoRoute: Routes = [
    {
        path: 'realname-info',
        component: RealnameInfoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.realnameInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'realname-info/:id/view',
        component: RealnameInfoDetailComponent,
        resolve: {
            realnameInfo: RealnameInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.realnameInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'realname-info/new',
        component: RealnameInfoUpdateComponent,
        resolve: {
            realnameInfo: RealnameInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.realnameInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'realname-info/:id/edit',
        component: RealnameInfoUpdateComponent,
        resolve: {
            realnameInfo: RealnameInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.realnameInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const realnameInfoPopupRoute: Routes = [
    {
        path: 'realname-info/:id/delete',
        component: RealnameInfoDeletePopupComponent,
        resolve: {
            realnameInfo: RealnameInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.realnameInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
