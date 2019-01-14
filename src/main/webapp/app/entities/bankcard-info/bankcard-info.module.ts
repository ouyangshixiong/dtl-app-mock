import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DtlAppMockSharedModule } from 'app/shared';
import {
    BankcardInfoComponent,
    BankcardInfoDetailComponent,
    BankcardInfoUpdateComponent,
    BankcardInfoDeletePopupComponent,
    BankcardInfoDeleteDialogComponent,
    bankcardInfoRoute,
    bankcardInfoPopupRoute
} from './';

const ENTITY_STATES = [...bankcardInfoRoute, ...bankcardInfoPopupRoute];

@NgModule({
    imports: [DtlAppMockSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BankcardInfoComponent,
        BankcardInfoDetailComponent,
        BankcardInfoUpdateComponent,
        BankcardInfoDeleteDialogComponent,
        BankcardInfoDeletePopupComponent
    ],
    entryComponents: [
        BankcardInfoComponent,
        BankcardInfoUpdateComponent,
        BankcardInfoDeleteDialogComponent,
        BankcardInfoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DtlAppMockBankcardInfoModule {}
