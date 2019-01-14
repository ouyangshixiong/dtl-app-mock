import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RealnameAuthRecord } from 'app/shared/model/realname-auth-record.model';
import { RealnameAuthRecordService } from './realname-auth-record.service';
import { RealnameAuthRecordComponent } from './realname-auth-record.component';
import { RealnameAuthRecordDetailComponent } from './realname-auth-record-detail.component';
import { RealnameAuthRecordUpdateComponent } from './realname-auth-record-update.component';
import { RealnameAuthRecordDeletePopupComponent } from './realname-auth-record-delete-dialog.component';
import { IRealnameAuthRecord } from 'app/shared/model/realname-auth-record.model';

@Injectable({ providedIn: 'root' })
export class RealnameAuthRecordResolve implements Resolve<IRealnameAuthRecord> {
    constructor(private service: RealnameAuthRecordService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RealnameAuthRecord> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RealnameAuthRecord>) => response.ok),
                map((realnameAuthRecord: HttpResponse<RealnameAuthRecord>) => realnameAuthRecord.body)
            );
        }
        return of(new RealnameAuthRecord());
    }
}

export const realnameAuthRecordRoute: Routes = [
    {
        path: 'realname-auth-record',
        component: RealnameAuthRecordComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.realnameAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'realname-auth-record/:id/view',
        component: RealnameAuthRecordDetailComponent,
        resolve: {
            realnameAuthRecord: RealnameAuthRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.realnameAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'realname-auth-record/new',
        component: RealnameAuthRecordUpdateComponent,
        resolve: {
            realnameAuthRecord: RealnameAuthRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.realnameAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'realname-auth-record/:id/edit',
        component: RealnameAuthRecordUpdateComponent,
        resolve: {
            realnameAuthRecord: RealnameAuthRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.realnameAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const realnameAuthRecordPopupRoute: Routes = [
    {
        path: 'realname-auth-record/:id/delete',
        component: RealnameAuthRecordDeletePopupComponent,
        resolve: {
            realnameAuthRecord: RealnameAuthRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.realnameAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
