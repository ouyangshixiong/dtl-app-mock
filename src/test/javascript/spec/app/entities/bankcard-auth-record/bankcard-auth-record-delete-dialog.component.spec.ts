/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DtlAppMockTestModule } from '../../../test.module';
import { BankcardAuthRecordDeleteDialogComponent } from 'app/entities/bankcard-auth-record/bankcard-auth-record-delete-dialog.component';
import { BankcardAuthRecordService } from 'app/entities/bankcard-auth-record/bankcard-auth-record.service';

describe('Component Tests', () => {
    describe('BankcardAuthRecord Management Delete Component', () => {
        let comp: BankcardAuthRecordDeleteDialogComponent;
        let fixture: ComponentFixture<BankcardAuthRecordDeleteDialogComponent>;
        let service: BankcardAuthRecordService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [BankcardAuthRecordDeleteDialogComponent]
            })
                .overrideTemplate(BankcardAuthRecordDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BankcardAuthRecordDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BankcardAuthRecordService);
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
