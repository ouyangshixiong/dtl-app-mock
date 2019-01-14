import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SiteAuthRecord } from 'app/shared/model/site-auth-record.model';
import { SiteAuthRecordService } from './site-auth-record.service';
import { SiteAuthRecordComponent } from './site-auth-record.component';
import { SiteAuthRecordDetailComponent } from './site-auth-record-detail.component';
import { SiteAuthRecordUpdateComponent } from './site-auth-record-update.component';
import { SiteAuthRecordDeletePopupComponent } from './site-auth-record-delete-dialog.component';
import { ISiteAuthRecord } from 'app/shared/model/site-auth-record.model';

@Injectable({ providedIn: 'root' })
export class SiteAuthRecordResolve implements Resolve<ISiteAuthRecord> {
    constructor(private service: SiteAuthRecordService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SiteAuthRecord> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SiteAuthRecord>) => response.ok),
                map((siteAuthRecord: HttpResponse<SiteAuthRecord>) => siteAuthRecord.body)
            );
        }
        return of(new SiteAuthRecord());
    }
}

export const siteAuthRecordRoute: Routes = [
    {
        path: 'site-auth-record',
        component: SiteAuthRecordComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.siteAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'site-auth-record/:id/view',
        component: SiteAuthRecordDetailComponent,
        resolve: {
            siteAuthRecord: SiteAuthRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.siteAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'site-auth-record/new',
        component: SiteAuthRecordUpdateComponent,
        resolve: {
            siteAuthRecord: SiteAuthRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.siteAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'site-auth-record/:id/edit',
        component: SiteAuthRecordUpdateComponent,
        resolve: {
            siteAuthRecord: SiteAuthRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.siteAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const siteAuthRecordPopupRoute: Routes = [
    {
        path: 'site-auth-record/:id/delete',
        component: SiteAuthRecordDeletePopupComponent,
        resolve: {
            siteAuthRecord: SiteAuthRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.siteAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
