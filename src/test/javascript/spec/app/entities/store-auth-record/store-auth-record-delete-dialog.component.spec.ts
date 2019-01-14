/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DtlAppMockTestModule } from '../../../test.module';
import { StoreAuthRecordDeleteDialogComponent } from 'app/entities/store-auth-record/store-auth-record-delete-dialog.component';
import { StoreAuthRecordService } from 'app/entities/store-auth-record/store-auth-record.service';

describe('Component Tests', () => {
    describe('StoreAuthRecord Management Delete Component', () => {
        let comp: StoreAuthRecordDeleteDialogComponent;
        let fixture: ComponentFixture<StoreAuthRecordDeleteDialogComponent>;
        let service: StoreAuthRecordService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [StoreAuthRecordDeleteDialogComponent]
            })
                .overrideTemplate(StoreAuthRecordDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StoreAuthRecordDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StoreAuthRecordService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
