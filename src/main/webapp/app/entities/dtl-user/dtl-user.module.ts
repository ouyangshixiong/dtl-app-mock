import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DtlAppMockSharedModule } from 'app/shared';
import {
    DTLUserComponent,
    DTLUserDetailComponent,
    DTLUserUpdateComponent,
    DTLUserDeletePopupComponent,
    DTLUserDeleteDialogComponent,
    dTLUserRoute,
    dTLUserPopupRoute
} from './';

const ENTITY_STATES = [...dTLUserRoute, ...dTLUserPopupRoute];

@NgModule({
    imports: [DtlAppMockSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DTLUserComponent,
        DTLUserDetailComponent,
        DTLUserUpdateComponent,
        DTLUserDeleteDialogComponent,
        DTLUserDeletePopupComponent
    ],
    entryComponents: [DTLUserComponent, DTLUserUpdateComponent, DTLUserDeleteDialogComponent, DTLUserDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DtlAppMockDTLUserModule {}
