import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SiteInfo } from 'app/shared/model/site-info.model';
import { SiteInfoService } from './site-info.service';
import { SiteInfoComponent } from './site-info.component';
import { SiteInfoDetailComponent } from './site-info-detail.component';
import { SiteInfoUpdateComponent } from './site-info-update.component';
import { SiteInfoDeletePopupComponent } from './site-info-delete-dialog.component';
import { ISiteInfo } from 'app/shared/model/site-info.model';

@Injectable({ providedIn: 'root' })
export class SiteInfoResolve implements Resolve<ISiteInfo> {
    constructor(private service: SiteInfoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SiteInfo> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SiteInfo>) => response.ok),
                map((siteInfo: HttpResponse<SiteInfo>) => siteInfo.body)
            );
        }
        return of(new SiteInfo());
    }
}

export const siteInfoRoute: Routes = [
    {
        path: 'site-info',
        component: SiteInfoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.siteInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'site-info/:id/view',
        component: SiteInfoDetailComponent,
        resolve: {
            siteInfo: SiteInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.siteInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'site-info/new',
        component: SiteInfoUpdateComponent,
        resolve: {
            siteInfo: SiteInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.siteInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'site-info/:id/edit',
        component: SiteInfoUpdateComponent,
        resolve: {
            siteInfo: SiteInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.siteInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const siteInfoPopupRoute: Routes = [
    {
        path: 'site-info/:id/delete',
        component: SiteInfoDeletePopupComponent,
        resolve: {
            siteInfo: SiteInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.siteInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
