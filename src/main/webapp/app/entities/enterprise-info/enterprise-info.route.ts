import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EnterpriseInfo } from 'app/shared/model/enterprise-info.model';
import { EnterpriseInfoService } from './enterprise-info.service';
import { EnterpriseInfoComponent } from './enterprise-info.component';
import { EnterpriseInfoDetailComponent } from './enterprise-info-detail.component';
import { EnterpriseInfoUpdateComponent } from './enterprise-info-update.component';
import { EnterpriseInfoDeletePopupComponent } from './enterprise-info-delete-dialog.component';
import { IEnterpriseInfo } from 'app/shared/model/enterprise-info.model';

@Injectable({ providedIn: 'root' })
export class EnterpriseInfoResolve implements Resolve<IEnterpriseInfo> {
    constructor(private service: EnterpriseInfoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EnterpriseInfo> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EnterpriseInfo>) => response.ok),
                map((enterpriseInfo: HttpResponse<EnterpriseInfo>) => enterpriseInfo.body)
            );
        }
        return of(new EnterpriseInfo());
    }
}

export const enterpriseInfoRoute: Routes = [
    {
        path: 'enterprise-info',
        component: EnterpriseInfoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.enterpriseInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'enterprise-info/:id/view',
        component: EnterpriseInfoDetailComponent,
        resolve: {
            enterpriseInfo: EnterpriseInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.enterpriseInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'enterprise-info/new',
        component: EnterpriseInfoUpdateComponent,
        resolve: {
            enterpriseInfo: EnterpriseInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.enterpriseInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'enterprise-info/:id/edit',
        component: EnterpriseInfoUpdateComponent,
        resolve: {
            enterpriseInfo: EnterpriseInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.enterpriseInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const enterpriseInfoPopupRoute: Routes = [
    {
        path: 'enterprise-info/:id/delete',
        component: EnterpriseInfoDeletePopupComponent,
        resolve: {
            enterpriseInfo: EnterpriseInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.enterpriseInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
