import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BankcardAuthRecord } from 'app/shared/model/bankcard-auth-record.model';
import { BankcardAuthRecordService } from './bankcard-auth-record.service';
import { BankcardAuthRecordComponent } from './bankcard-auth-record.component';
import { BankcardAuthRecordDetailComponent } from './bankcard-auth-record-detail.component';
import { BankcardAuthRecordUpdateComponent } from './bankcard-auth-record-update.component';
import { BankcardAuthRecordDeletePopupComponent } from './bankcard-auth-record-delete-dialog.component';
import { IBankcardAuthRecord } from 'app/shared/model/bankcard-auth-record.model';

@Injectable({ providedIn: 'root' })
export class BankcardAuthRecordResolve implements Resolve<IBankcardAuthRecord> {
    constructor(private service: BankcardAuthRecordService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<BankcardAuthRecord> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<BankcardAuthRecord>) => response.ok),
                map((bankcardAuthRecord: HttpResponse<BankcardAuthRecord>) => bankcardAuthRecord.body)
            );
        }
        return of(new BankcardAuthRecord());
    }
}

export const bankcardAuthRecordRoute: Routes = [
    {
        path: 'bankcard-auth-record',
        component: BankcardAuthRecordComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.bankcardAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bankcard-auth-record/:id/view',
        component: BankcardAuthRecordDetailComponent,
        resolve: {
            bankcardAuthRecord: BankcardAuthRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.bankcardAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bankcard-auth-record/new',
        component: BankcardAuthRecordUpdateComponent,
        resolve: {
            bankcardAuthRecord: BankcardAuthRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.bankcardAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bankcard-auth-record/:id/edit',
        component: BankcardAuthRecordUpdateComponent,
        resolve: {
            bankcardAuthRecord: BankcardAuthRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.bankcardAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const bankcardAuthRecordPopupRoute: Routes = [
    {
        path: 'bankcard-auth-record/:id/delete',
        component: BankcardAuthRecordDeletePopupComponent,
        resolve: {
            bankcardAuthRecord: BankcardAuthRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.bankcardAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
