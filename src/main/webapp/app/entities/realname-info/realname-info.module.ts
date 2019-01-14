import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DtlAppMockSharedModule } from 'app/shared';
import {
    RealnameInfoComponent,
    RealnameInfoDetailComponent,
    RealnameInfoUpdateComponent,
    RealnameInfoDeletePopupComponent,
    RealnameInfoDeleteDialogComponent,
    realnameInfoRoute,
    realnameInfoPopupRoute
} from './';

const ENTITY_STATES = [...realnameInfoRoute, ...realnameInfoPopupRoute];

@NgModule({
    imports: [DtlAppMockSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RealnameInfoComponent,
        RealnameInfoDetailComponent,
        RealnameInfoUpdateComponent,
        RealnameInfoDeleteDialogComponent,
        RealnameInfoDeletePopupComponent
    ],
    entryComponents: [
        RealnameInfoComponent,
        RealnameInfoUpdateComponent,
        RealnameInfoDeleteDialogComponent,
        RealnameInfoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DtlAppMockRealnameInfoModule {}
