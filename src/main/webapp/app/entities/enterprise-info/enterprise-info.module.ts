import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DtlAppMockSharedModule } from 'app/shared';
import {
    EnterpriseInfoComponent,
    EnterpriseInfoDetailComponent,
    EnterpriseInfoUpdateComponent,
    EnterpriseInfoDeletePopupComponent,
    EnterpriseInfoDeleteDialogComponent,
    enterpriseInfoRoute,
    enterpriseInfoPopupRoute
} from './';

const ENTITY_STATES = [...enterpriseInfoRoute, ...enterpriseInfoPopupRoute];

@NgModule({
    imports: [DtlAppMockSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EnterpriseInfoComponent,
        EnterpriseInfoDetailComponent,
        EnterpriseInfoUpdateComponent,
        EnterpriseInfoDeleteDialogComponent,
        EnterpriseInfoDeletePopupComponent
    ],
    entryComponents: [
        EnterpriseInfoComponent,
        EnterpriseInfoUpdateComponent,
        EnterpriseInfoDeleteDialogComponent,
        EnterpriseInfoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DtlAppMockEnterpriseInfoModule {}
