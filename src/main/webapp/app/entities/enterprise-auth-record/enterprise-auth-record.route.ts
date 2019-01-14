import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EnterpriseAuthRecord } from 'app/shared/model/enterprise-auth-record.model';
import { EnterpriseAuthRecordService } from './enterprise-auth-record.service';
import { EnterpriseAuthRecordComponent } from './enterprise-auth-record.component';
import { EnterpriseAuthRecordDetailComponent } from './enterprise-auth-record-detail.component';
import { EnterpriseAuthRecordUpdateComponent } from './enterprise-auth-record-update.component';
import { EnterpriseAuthRecordDeletePopupComponent } from './enterprise-auth-record-delete-dialog.component';
import { IEnterpriseAuthRecord } from 'app/shared/model/enterprise-auth-record.model';

@Injectable({ providedIn: 'root' })
export class EnterpriseAuthRecordResolve implements Resolve<IEnterpriseAuthRecord> {
    constructor(private service: EnterpriseAuthRecordService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EnterpriseAuthRecord> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EnterpriseAuthRecord>) => response.ok),
                map((enterpriseAuthRecord: HttpResponse<EnterpriseAuthRecord>) => enterpriseAuthRecord.body)
            );
        }
        return of(new EnterpriseAuthRecord());
    }
}

export const enterpriseAuthRecordRoute: Routes = [
    {
        path: 'enterprise-auth-record',
        component: EnterpriseAuthRecordComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.enterpriseAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'enterprise-auth-record/:id/view',
        component: EnterpriseAuthRecordDetailComponent,
        resolve: {
            enterpriseAuthRecord: EnterpriseAuthRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.enterpriseAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'enterprise-auth-record/new',
        component: EnterpriseAuthRecordUpdateComponent,
        resolve: {
            enterpriseAuthRecord: EnterpriseAuthRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.enterpriseAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'enterprise-auth-record/:id/edit',
        component: EnterpriseAuthRecordUpdateComponent,
        resolve: {
            enterpriseAuthRecord: EnterpriseAuthRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.enterpriseAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const enterpriseAuthRecordPopupRoute: Routes = [
    {
        path: 'enterprise-auth-record/:id/delete',
        component: EnterpriseAuthRecordDeletePopupComponent,
        resolve: {
            enterpriseAuthRecord: EnterpriseAuthRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.enterpriseAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
