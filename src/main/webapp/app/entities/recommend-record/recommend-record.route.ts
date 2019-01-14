import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RecommendRecord } from 'app/shared/model/recommend-record.model';
import { RecommendRecordService } from './recommend-record.service';
import { RecommendRecordComponent } from './recommend-record.component';
import { RecommendRecordDetailComponent } from './recommend-record-detail.component';
import { RecommendRecordUpdateComponent } from './recommend-record-update.component';
import { RecommendRecordDeletePopupComponent } from './recommend-record-delete-dialog.component';
import { IRecommendRecord } from 'app/shared/model/recommend-record.model';

@Injectable({ providedIn: 'root' })
export class RecommendRecordResolve implements Resolve<IRecommendRecord> {
    constructor(private service: RecommendRecordService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RecommendRecord> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RecommendRecord>) => response.ok),
                map((recommendRecord: HttpResponse<RecommendRecord>) => recommendRecord.body)
            );
        }
        return of(new RecommendRecord());
    }
}

export const recommendRecordRoute: Routes = [
    {
        path: 'recommend-record',
        component: RecommendRecordComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.recommendRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'recommend-record/:id/view',
        component: RecommendRecordDetailComponent,
        resolve: {
            recommendRecord: RecommendRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.recommendRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'recommend-record/new',
        component: RecommendRecordUpdateComponent,
        resolve: {
            recommendRecord: RecommendRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.recommendRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'recommend-record/:id/edit',
        component: RecommendRecordUpdateComponent,
        resolve: {
            recommendRecord: RecommendRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.recommendRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const recommendRecordPopupRoute: Routes = [
    {
        path: 'recommend-record/:id/delete',
        component: RecommendRecordDeletePopupComponent,
        resolve: {
            recommendRecord: RecommendRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.recommendRecord.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
