/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { DtlAppMockTestModule } from '../../../test.module';
import { DTLUserDeleteDialogComponent } from 'app/entities/dtl-user/dtl-user-delete-dialog.component';
import { DTLUserService } from 'app/entities/dtl-user/dtl-user.service';

describe('Component Tests', () => {
    describe('DTLUser Management Delete Component', () => {
        let comp: DTLUserDeleteDialogComponent;
        let fixture: ComponentFixture<DTLUserDeleteDialogComponent>;
        let service: DTLUserService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [DTLUserDeleteDialogComponent]
            })
                .overrideTemplate(DTLUserDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DTLUserDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DTLUserService);
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
