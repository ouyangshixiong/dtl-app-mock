import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BankcardInfo } from 'app/shared/model/bankcard-info.model';
import { BankcardInfoService } from './bankcard-info.service';
import { BankcardInfoComponent } from './bankcard-info.component';
import { BankcardInfoDetailComponent } from './bankcard-info-detail.component';
import { BankcardInfoUpdateComponent } from './bankcard-info-update.component';
import { BankcardInfoDeletePopupComponent } from './bankcard-info-delete-dialog.component';
import { IBankcardInfo } from 'app/shared/model/bankcard-info.model';

@Injectable({ providedIn: 'root' })
export class BankcardInfoResolve implements Resolve<IBankcardInfo> {
    constructor(private service: BankcardInfoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<BankcardInfo> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<BankcardInfo>) => response.ok),
                map((bankcardInfo: HttpResponse<BankcardInfo>) => bankcardInfo.body)
            );
        }
        return of(new BankcardInfo());
    }
}

export const bankcardInfoRoute: Routes = [
    {
        path: 'bankcard-info',
        component: BankcardInfoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.bankcardInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bankcard-info/:id/view',
        component: BankcardInfoDetailComponent,
        resolve: {
            bankcardInfo: BankcardInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.bankcardInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bankcard-info/new',
        component: BankcardInfoUpdateComponent,
        resolve: {
            bankcardInfo: BankcardInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.bankcardInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bankcard-info/:id/edit',
        component: BankcardInfoUpdateComponent,
        resolve: {
            bankcardInfo: BankcardInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.bankcardInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const bankcardInfoPopupRoute: Routes = [
    {
        path: 'bankcard-info/:id/delete',
        component: BankcardInfoDeletePopupComponent,
        resolve: {
            bankcardInfo: BankcardInfoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.bankcardInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
