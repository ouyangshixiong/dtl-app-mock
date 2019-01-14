import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DtlAppMockSharedModule } from 'app/shared';
import {
    RecommendRecordComponent,
    RecommendRecordDetailComponent,
    RecommendRecordUpdateComponent,
    RecommendRecordDeletePopupComponent,
    RecommendRecordDeleteDialogComponent,
    recommendRecordRoute,
    recommendRecordPopupRoute
} from './';

const ENTITY_STATES = [...recommendRecordRoute, ...recommendRecordPopupRoute];

@NgModule({
    imports: [DtlAppMockSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RecommendRecordComponent,
        RecommendRecordDetailComponent,
        RecommendRecordUpdateComponent,
        RecommendRecordDeleteDialogComponent,
        RecommendRecordDeletePopupComponent
    ],
    entryComponents: [
        RecommendRecordComponent,
        RecommendRecordUpdateComponent,
        RecommendRecordDeleteDialogComponent,
        RecommendRecordDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DtlAppMockRecommendRecordModule {}
