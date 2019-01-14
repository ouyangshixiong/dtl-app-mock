import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DtlAppMockSharedModule } from 'app/shared';
import {
    EnterpriseAuthRecordComponent,
    EnterpriseAuthRecordDetailComponent,
    EnterpriseAuthRecordUpdateComponent,
    EnterpriseAuthRecordDeletePopupComponent,
    EnterpriseAuthRecordDeleteDialogComponent,
    enterpriseAuthRecordRoute,
    enterpriseAuthRecordPopupRoute
} from './';

const ENTITY_STATES = [...enterpriseAuthRecordRoute, ...enterpriseAuthRecordPopupRoute];

@NgModule({
    imports: [DtlAppMockSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EnterpriseAuthRecordComponent,
        EnterpriseAuthRecordDetailComponent,
        EnterpriseAuthRecordUpdateComponent,
        EnterpriseAuthRecordDeleteDialogComponent,
        EnterpriseAuthRecordDeletePopupComponent
    ],
    entryComponents: [
        EnterpriseAuthRecordComponent,
        EnterpriseAuthRecordUpdateComponent,
        EnterpriseAuthRecordDeleteDialogComponent,
        EnterpriseAuthRecordDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DtlAppMockEnterpriseAuthRecordModule {}
